import { ChangeDetectorRef, Component, TemplateRef, ViewChild } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { STChange, STColumn, STComponent, STData } from "@delon/abc/st";
import { _HttpClient } from "@delon/theme";
import { NzSafeAny } from "ng-zorro-antd/core/types";
import { NzMessageService } from "ng-zorro-antd/message";
import { NzModalComponent, NzModalService } from "ng-zorro-antd/modal";
import { map, tap } from "rxjs/operators";
import { Staff, StaffControllerService, User } from "src/shared/api";

@Component({ selector: "staff-manage", templateUrl: "./staff-manage.component.html" })
export class StaffManageComponent {
    @ViewChild("createModal")
    createModal!: NzModalComponent;
    createStaffForm!: FormGroup
    total: number = 0;
    userOptions: User[] = [
        { id: 1, username: "张三" },
        { id: 2, username: "李四" }

    ]
    jobOptions = ["领导", "员工"];
    // constructor() { }
    q: {
        pi: number;
        ps: number;
        no: string;
        sorter: string;
        status: number | null;
        statusList: NzSafeAny[];
    } = {
            pi: 1,
            ps: 5,
            no: '',
            sorter: '',
            status: null,
            statusList: []
        };
    data: Staff[] = [];
    loading = false;
    status = [
        { index: 0, text: '关闭', value: false, type: 'default', checked: false },
        {
            index: 1,
            text: '运行中',
            value: false,
            type: 'processing',
            checked: false
        },
        { index: 2, text: '已上线', value: false, type: 'success', checked: false },
        { index: 3, text: '异常', value: false, type: 'error', checked: false }
    ];
    @ViewChild('st', { static: true })
    st!: STComponent;
    columns: STColumn[] = [
        { title: '', index: 'key', type: 'checkbox' },
        { title: '规则编号', index: 'id' },
        { title: '分组', index: 'groupname' },
        {
            title: '职位',
            index: 'job',

        },
        {
            title: '状态',
            index: 'status',
            render: 'status',
            // filter: {
            //     menus: this.status,
            //     fn: (filter, record) => record.status === filter.index
            // }
        },
        {
            title: '更新时间',
            index: 'updatedAt',
            type: 'date',
            sort: {
                compare: (a, b) => a.updatedAt - b.updatedAt
            }
        },
        {
            title: '操作',
            buttons: [
                {
                    iif: (item) => item.status,
                    text: '禁用',
                    click: item => this.staffControllerService.disableStaffByIdUsingGET(item.id, false).toPromise().then(r => this.getData())
                },
                {
                    iif: (item) => !item.status,
                    text: '启用',
                    click: item => this.staffControllerService.disableStaffByIdUsingGET(item.id, true).toPromise().then(r => this.getData())
                },

                {
                    text: '删除',
                    click: item => this.staffControllerService.deleteStaffByIdUsingDELETE(item.id).toPromise().then(r => this.getData())
                }
            ]
        }
    ];
    selectedRows: STData[] = [];
    description = '';
    totalCallNo = 0;
    expandForm = false;

    constructor(private http: _HttpClient,
        public msg: NzMessageService,
        private modalSrv: NzModalService,
        private cdr: ChangeDetectorRef,
        private staffControllerService: StaffControllerService,
        private fb: FormBuilder
    ) {
        this.createStaffForm = this.fb.group({
            user: [null, [Validators.required]],
            // username: [null, [Validators.required]],
            groupname: [null, [Validators.required]],
            job: [null, [Validators.required]],
            nickname: [null, [Validators.required]],
            phonenumber: [null, [Validators.required, Validators.pattern(/^1[3-9]\d{9}$/g)]]
        });
    }

    async ngOnInit() {
        let data = await this.staffControllerService.queryNotStaffUserUsingGET().toPromise();
        this.userOptions = data.data as any;
        // this.createModal.open()
        // await this.staffControllerService.getStaffUsingGET('', 0, 10).toPromise();
        this.getData();
    }

    async getData() {
        this.loading = true;
        let rtn = await this.staffControllerService.getStaffUsingGET(this.q.no, this.q.pi - 1, this.q.ps).toPromise();
        this.data = rtn.data?.data as any[];
        this.total = rtn.data?.total as any;
        this.loading = false;
        //   rtn.
        // this.q.statusList = this.status.filter(w => w.checked).map(item => item.index);
        // if (this.q.status !== null && this.q.status > -1) {
        //     this.q.statusList.push(this.q.status);
        // }
        // this.http
        //     .get('/rule', this.q)
        //     .pipe(
        //         map((list: Array<{ status: number; statusText: string; statusType: string }>) =>
        //             list.map(i => {
        //                 const statusItem = this.status[i.status];
        //                 i.statusText = statusItem.text;
        //                 i.statusType = statusItem.type;
        //                 return i;
        //             })
        //         ),
        //         tap(() => (this.loading = false))
        //     )
        //     .subscribe(res => {
        //         this.data = res;
        //         this.cdr.detectChanges();
        //     });
    }

    stChange(e: STChange): void {
        switch (e.type) {
            case 'checkbox':
                this.selectedRows = e.checkbox!;
                this.totalCallNo = this.selectedRows.reduce((total, cv) => total + cv.callNo, 0);
                this.cdr.detectChanges();
                break;
            case 'filter':

                this.getData();
                break;
            case 'ps':
            case 'pi':
                this.q.pi = e.pi;
                this.q.ps = e.ps;
                this.getData();
                break;
        }
    }

    remove(): void {
        this.http.delete('/rule', { nos: this.selectedRows.map(i => i.no).join(',') }).subscribe(() => {
            this.getData();
            this.st.clearCheck();
        });
    }

    approval(): void {
        this.msg.success(`审批了 ${this.selectedRows.length} 笔`);
    }

    add = async () => {
        let data: Staff & { user: User } = Object.assign(this.createStaffForm.value);
        data.userid = data.user.id as any;
        data.username = data.user.username;

        await this.staffControllerService.addStaffUsingPOST(data).toPromise();

    }
    refresh() {
        this.getData();
    }

    reset(): void {
        // wait form reset updated finished
        setTimeout(() => this.getData());
    }
}
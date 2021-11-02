import { Component } from '@angular/core';
import { DragulaService } from 'ng2-dragula';
import { Subscription } from 'rxjs';

@Component({
  selector: 'form-builder-content',
  templateUrl: './form-builder-content.component.html',
  styleUrls: ['./form-builder-content.component.css']
})
export class FormBuilderContentComponent {
  subs = new Subscription();
  preview = false;
  controls: any[] = [];
  showAlainCode = false;
  selectedControl: any;

  show = false;
  get body(): any {
    return { type: 'page', body: { type: 'form', body: this.controls, expand: true }, expand: true };
  }
  constructor(private dragulaService: DragulaService) {
    this.subs.add(
      dragulaService.dropModel('form-item').subscribe(({ el, target, source, sourceModel, targetModel, item }) => {
        console.log('dropModel:');
        console.log(el);
        console.log(source);
        console.log(target);
        console.log(sourceModel);
        console.log(targetModel);
        console.log(item);
      })
    );
    this.subs.add(
      dragulaService.removeModel('form-item').subscribe(({ el, source, item, sourceModel }) => {
        console.log('removeModel:');
        console.log(el);
        console.log(source);
        console.log(sourceModel);
        console.log(item);
      })
    );

    // dragulaService.createGroup('form-item', {
    //   copy: (el, source) => {
    //     debugger;
    //     return source.id === 'left';
    //   },
    //   accepts: (el, target: any, source, sibling) => {
    //     // To avoid dragging from right to left container
    //     debugger;
    //     return target.id !== 'left';
    //   }
    // });
  }
  refresh() {
    this.show = false;
    setTimeout(() => {
      this.show = true;
    }, 200);
  }
  ngAfterViewInit() {
    let group = this.dragulaService.find('form-item');
    this.dragulaService.dropModel('form-item').subscribe(rtn => {
      rtn;
      debugger;
    });
    debugger;
  }
}

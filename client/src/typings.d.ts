// # 3rd Party Library
// If the library doesn't have typings available at `@types/`,

// you can still use it by manually adding typings for it
declare var amis: any;
declare class Handlebar {
  compile(content: string): any;
  registerHelper(templateName: string, callback: Function): void;
  registerPartial(name: string, template: string): void;
}
declare var Handlebars: Handlebar;
declare module 'path-browserify' {
  function resolve(...arg: string[]): string;
  function basename(s: string): string;
}

declare var hljs: Hljs;

interface Hljs {
  highlightAll(): void;
}

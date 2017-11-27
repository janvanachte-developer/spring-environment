import { SpringEnvironmentUiNgPage } from './app.po';

describe('spring-environment-ui-ng App', () => {
  let page: SpringEnvironmentUiNgPage;

  beforeEach(() => {
    page = new SpringEnvironmentUiNgPage();
  });

  it('should display welcome message', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('Welcome to app!');
  });
});

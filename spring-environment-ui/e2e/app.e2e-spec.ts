import { SpringEnvironmentUiPage } from './app.po';

describe('spring-environment-ui App', () => {
  let page: SpringEnvironmentUiPage;

  beforeEach(() => {
    page = new SpringEnvironmentUiPage();
  });

  it('should display welcome message', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('Welcome to app!');
  });
});

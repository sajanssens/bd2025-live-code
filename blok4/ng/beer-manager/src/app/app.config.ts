import {ApplicationConfig, LOCALE_ID, provideBrowserGlobalErrorListeners} from '@angular/core';
import {provideRouter} from '@angular/router';

import {routes} from './app.routes';
import {registerLocaleData} from '@angular/common';
import localeNL from '@angular/common/locales/nl';
import {provideHttpClient} from '@angular/common/http';

registerLocaleData(localeNL);

export const appConfig: ApplicationConfig = {
  providers: [
    provideBrowserGlobalErrorListeners(),
    provideRouter(routes),
    {provide: LOCALE_ID, useValue: 'nl-NL'},
    provideHttpClient()
  ]
};

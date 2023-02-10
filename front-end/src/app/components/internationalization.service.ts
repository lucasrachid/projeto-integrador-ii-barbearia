import {Injectable} from "@angular/core";
import {TranslateService} from "@ngx-translate/core";

@Injectable({
  providedIn: 'root'
})
export class InternationalizationService {

  localeStorageItemName = 'projeto-integrador-ii-barbearia-LOCALE';

  constructor(private translate: TranslateService) { }

  initializeSystemTranslate() {
    this.translate.addLangs(['pt-br', 'en', 'es']);
    const localeFromLocalStorage = this.getUserLocale();
    if(localeFromLocalStorage !== null) {
      const toUse = localeFromLocalStorage.match(/pt-br|en|es/) ? localeFromLocalStorage : 'pt-br';
      this.translate.use(toUse);
    } else {
      const browserCultureLang = this.translate.getBrowserCultureLang();
      const toUse = browserCultureLang!.match(/pt-br|en|es/) ? browserCultureLang : 'pt-br';
      if (toUse != null) {
        this.translate.use(toUse);
      }
    }
  }

  setUserLocale(locale: string) {
    this.translate.use(locale);
    localStorage.setItem(this.localeStorageItemName, locale);
  }

  getUserLocale() {
    let locale = localStorage.getItem(this.localeStorageItemName);
    if(locale !== null) {
      return locale;
    } else {
      return 'pt-br';
    }
  }

}

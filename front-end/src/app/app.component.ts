import { Component } from '@angular/core';
import { InternationalizationService } from './components/internationalization.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'Barbearia';

  constructor(private internationalizationService: InternationalizationService) {
    internationalizationService.initializeSystemTranslate();
  }
}

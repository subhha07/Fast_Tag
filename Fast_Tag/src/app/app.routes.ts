import { Routes } from '@angular/router';
import { TollcalculatorComponent } from './toll-calculator/toll-calculator.component';
import { RegistrationFormComponent } from './registration-form/registration-form.component';
import { PaymentSelectionComponent } from './payment-selection/payment-selection.component';

export const routes: Routes = [
    {
      path: 'fasttag',
      component: TollcalculatorComponent
    },
    {
      path: '',
      component: TollcalculatorComponent
    },
    {
      path: 'register',
      component: RegistrationFormComponent
    },
    {
        path: 'pay',
        component: PaymentSelectionComponent
    }
];

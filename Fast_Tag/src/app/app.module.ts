// filepath: /Users/subha-21965/fasttag/src/app/app.module.ts
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms'; // Import ReactiveFormsModule
import { HttpClient, HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { RegistrationFormComponent } from './registration-form/registration-form.component';
import { FastTagService } from './fast-tag.service';
import { TollcalculatorComponent } from './toll-calculator/toll-calculator.component';
import { PaymentSelectionComponent } from './payment-selection/payment-selection.component';

@NgModule({
  declarations: [
    AppComponent,
    TollcalculatorComponent,
    RegistrationFormComponent,
    PaymentSelectionComponent
    HttpClient,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule, // Add ReactiveFormsModule here
    HttpClientModule
  ],
  providers: [FastTagService, HttpClient],
  bootstrap: [AppComponent, HttpClient],
})
export class AppModule {}
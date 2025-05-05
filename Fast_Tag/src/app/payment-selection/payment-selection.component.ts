import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Router, RouterModule } from '@angular/router';
import { FastTagService } from '../fast-tag.service';
import { HttpClient, HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-payment-selection',
  templateUrl: './payment-selection.component.html',
  styleUrls: ['./payment-selection.component.css'],
  standalone: true,
  imports: [
    CommonModule,
    HttpClientModule,
    ReactiveFormsModule,
    RouterModule
  ]
})
export class PaymentSelectionComponent implements OnInit {
  form: FormGroup;
  paymentOptions = [
    { label: 'Fast Tag Wallet', value: 'FASTTAG_WALLET' },
    { label: 'Exempted Vehicle', value: 'EXEMPTED_VEHICLE' },
    { label: 'Cash', value: 'CASH' }
  ];
  
  isLoading = false;
  infoMessage = '';

  constructor
  (
    public router: Router,
    public fastTagService: FastTagService,
    private http: HttpClient
  )
  {
    this.form = new FormGroup({
      paymentType: new FormControl('CASH')
    });
  }

  updateLogAndRedirect() {
    if (this.form.valid) {
      this.isLoading = true;
      this.infoMessage = '';

      let { vehicleNumber, fasttagId, totalAmount } = this.fastTagService.isRegisteredFormGroup.value;
      let { paymentType } = this.form.value;
      console.log(paymentType);
      

      this.http.post('http://localhost:8080/FASTTAG/pay', {
        vehicleNumber: vehicleNumber,
        fasttagId: fasttagId,
        totalAmount: totalAmount,
        paymentType: paymentType
      }).subscribe(res => {
        console.log(res);
        
      });
  
      setTimeout(() => {
        this.isLoading = false;
        this.infoMessage = 'Page is redirected to home';

        this.fastTagService.isRegisteredFormGroup.reset();
        this.fastTagService.isRegisteredFormGroup.get('vehicleNumber')?.enable();

        setTimeout(() => {
          this.router.navigateByUrl('/fasttag');
        }, 1500);
  
      }, 1000);
    }
  }

  ngOnInit()
  {
   
  }
}

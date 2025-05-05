import { Component, OnInit } from '@angular/core';
import { FormGroup, ReactiveFormsModule } from '@angular/forms';
import { FastTagService } from '../fast-tag.service';
import { CommonModule } from '@angular/common';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { Router, RouterModule } from '@angular/router';

@Component({
  selector: 'app-registration-form',
  templateUrl: './registration-form.component.html',
  styleUrl: './registration-form.component.css',
  standalone: true,
  imports: [
    CommonModule,
    HttpClientModule,
    ReactiveFormsModule,
    RouterModule
  ]
})
export class RegistrationFormComponent implements OnInit {
  form: FormGroup;

  vehicleTypes: string[] = [
    'Car/Jeep/Van',
    'LCV',
    'Bus/Truck',
    'Upto 3 Axle Vehicle',
    '4 to 6 Axle',
    'HCM/EME',
    '7 or more Axle'
  ];

  readonly REGISTRATION_FEE = 50;

  readonly FARE_MAP: Record<string, number> = {
    'Car/Jeep/Van': 85,
    'LCV': 135,
    'Bus/Truck': 285,
    'Upto 3 Axle Vehicle': 315,
    '4 to 6 Axle': 450,
    'HCM/EME': 450,
    '7 or more Axle': 550
  };
  

  constructor(
    public fastTagService: FastTagService,
    public router: Router,
    private http: HttpClient
  ){
    this.form = this.fastTagService.isRegisteredFormGroup;
  }

  ngOnInit(){
    const { vehicleNumber, ownerName, vehicleType } = this.form.value;
    this.fastTagService.readonlyMode = !!(vehicleNumber && ownerName && vehicleType);

    if (this.fastTagService.readonlyMode) {
      this.form.disable();
    }

    if (!this.fastTagService.readonlyMode) {
      this.form.get('vehicleType')?.setValue('');
    }
  }

  onProceedToPayment(){
    this.fastTagService.showFare = true;
    this.router.navigateByUrl('/pay');
    
    if(!this.fastTagService.readonlyMode)
    {
      
      const { vehicleNumber, vehicleType, ownerName } = this.form.value;

      const fare = this.FARE_MAP[vehicleType] ?? 0;
      const registrationFee = this.REGISTRATION_FEE;
      const total = fare + registrationFee;
      this.fastTagService.isRegisteredFormGroup.patchValue({
        totalAmount: total
      });
      
      this.http.post('http://localhost:8080/FASTTAG/register', {
        vehicleNumber: vehicleNumber,
        vehicleType: vehicleType,
        ownerName: ownerName
      }).subscribe(res => {
        console.log(res);
        
      });
    }
  }
}


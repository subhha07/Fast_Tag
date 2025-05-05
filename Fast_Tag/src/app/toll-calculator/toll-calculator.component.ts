import { CommonModule } from '@angular/common';
import { HttpClient, HttpClientModule, HttpParams } from '@angular/common/http';
import { Component } from '@angular/core';
import { ReactiveFormsModule, FormBuilder, FormGroup } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { FastTagService } from '../fast-tag.service';

@Component({
  selector: 'toll-calculator',
  templateUrl: './toll-calculator.component.html',
  styleUrl: './toll-calculator.component.css',
  standalone: true,
  imports: [
    CommonModule,
    HttpClientModule,
    ReactiveFormsModule,
    RouterModule
  ]
})

export class TollcalculatorComponent
{
  public data: any;
  public isRegisteredUser: boolean = true;
  public submitted: boolean = false;

  constructor(
    private http: HttpClient,
    private router: Router,
    public fastTagService: FastTagService
  ){}
  go()
  {    
    let vehicleNumber = this.fastTagService.isRegisteredFormGroup.get('vehicleNumber')?.value;
    if (vehicleNumber!=null)
    {
      vehicleNumber = vehicleNumber.trim();
      this.http.post('http://localhost:8080/FASTTAG/fasttag', { vehicleNumber: vehicleNumber }).subscribe(res => {
        this.data = res;
        this.submitted = true;
        if(this.data!=null)
        {
          this.isRegisteredUser = this.data.isRegisteredUser;
          if(this.isRegisteredUser)
          {
            this.fastTagService.isRegisteredFormGroup = this.fastTagService.fb.group({
              ownerName: this.data.ownerName,
              vehicleType: this.data.vehicleType,
              amount: this.data.amount,
              vehicleNumber: vehicleNumber,
              totalAmount: this.data.totalAmount,
              fasttagId: this.data.fasttagId
            });
            console.log(this.fastTagService.fb);
            
            this.router.navigateByUrl('/register');
          }
        }
        console.log(this.isRegisteredUser);
      })
    }
    else
    {
      alert('Please enter a vehicle number.');
    }
  }

  registerVehicle() {
    this.router.navigateByUrl('/register');
    console.log('Navigating to vehicle registration');
  }
  
  exit() {
    this.fastTagService.isRegisteredFormGroup.reset();
    this.submitted = false;
    this.isRegisteredUser = true;
    this.fastTagService.readonlyMode=false;
  }
}

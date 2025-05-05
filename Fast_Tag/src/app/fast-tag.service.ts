// fast-tag.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { FormBuilder, FormGroup } from '@angular/forms';
@Injectable({
 providedIn: 'root',
})
export class FastTagService {
    public isRegisteredFormGroup: FormGroup;
    readonlyMode = false;
    showFare = false;
  
    constructor(public fb: FormBuilder) {
      this.isRegisteredFormGroup = this.fb.group({
        vehicleNumber: [''],
        ownerName: [''],
        vehicleType: [''],
        totalAmount: [''],
        fasttagId: ['']
      });
    }
  }
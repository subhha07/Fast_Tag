import { ComponentFixture, TestBed } from '@angular/core/testing';
import { TollcalculatorComponent } from './toll-calculator.component';

describe('TollcalculatorComponent', () => {
  let component: TollcalculatorComponent;
  let fixture: ComponentFixture<TollcalculatorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TollcalculatorComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TollcalculatorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

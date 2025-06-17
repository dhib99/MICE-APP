import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ExpositionComponent } from './exposition.component';

describe('ExpositionComponent', () => {
  let component: ExpositionComponent;
  let fixture: ComponentFixture<ExpositionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ExpositionComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ExpositionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

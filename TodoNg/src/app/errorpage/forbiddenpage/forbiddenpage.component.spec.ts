import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ForbiddenpageComponent } from './forbiddenpage.component';

describe('ForbiddenpageComponent', () => {
  let component: ForbiddenpageComponent;
  let fixture: ComponentFixture<ForbiddenpageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ForbiddenpageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ForbiddenpageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

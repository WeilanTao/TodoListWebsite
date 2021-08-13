import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HeaderHomeParentComponent } from './header-home-parent.component';

describe('HeaderHomeParentComponent', () => {
  let component: HeaderHomeParentComponent;
  let fixture: ComponentFixture<HeaderHomeParentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HeaderHomeParentComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HeaderHomeParentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

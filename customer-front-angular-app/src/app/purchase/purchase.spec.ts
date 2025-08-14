import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Purchase } from './purchase';

describe('Purchase', () => {
  let component: Purchase;
  let fixture: ComponentFixture<Purchase>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [Purchase]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Purchase);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

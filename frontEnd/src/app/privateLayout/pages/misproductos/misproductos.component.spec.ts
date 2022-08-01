import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MisproductosComponent } from './misproductos.component';

describe('MisproductosComponent', () => {
  let component: MisproductosComponent;
  let fixture: ComponentFixture<MisproductosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MisproductosComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MisproductosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

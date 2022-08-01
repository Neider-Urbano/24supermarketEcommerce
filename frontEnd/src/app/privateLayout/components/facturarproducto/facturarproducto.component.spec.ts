import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FacturarproductoComponent } from './facturarproducto.component';

describe('FacturarproductoComponent', () => {
  let component: FacturarproductoComponent;
  let fixture: ComponentFixture<FacturarproductoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FacturarproductoComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FacturarproductoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

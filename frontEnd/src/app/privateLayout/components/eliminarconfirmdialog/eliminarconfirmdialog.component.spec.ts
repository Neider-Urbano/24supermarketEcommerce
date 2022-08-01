import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EliminarconfirmdialogComponent } from './eliminarconfirmdialog.component';

describe('EliminarconfirmdialogComponent', () => {
  let component: EliminarconfirmdialogComponent;
  let fixture: ComponentFixture<EliminarconfirmdialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EliminarconfirmdialogComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EliminarconfirmdialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

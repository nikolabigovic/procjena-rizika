import { TestBed } from '@angular/core/testing';
import { Indikator } from './indikator';

describe('Indikator', () => {
  let service: Indikator;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(Indikator);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

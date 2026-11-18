import { TestBed } from '@angular/core/testing';
import { Procjena } from './procjena';

describe('Procjena', () => {
  let service: Procjena;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(Procjena);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

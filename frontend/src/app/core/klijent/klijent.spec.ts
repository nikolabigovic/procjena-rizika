import { TestBed } from '@angular/core/testing';
import { KlijentService } from './klijent';

describe('Klijent', () => {
  let service: KlijentService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(KlijentService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

import { TestBed } from '@angular/core/testing';
import { FizickoLice } from './fizicko-lice';

describe('FizickoLice', () => {
  let service: FizickoLice;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FizickoLice);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

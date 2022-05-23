import { formatPrice } from '../formatters';

describe('formatPrice for prositive number', () => {
 
    test('formatPrice should format number pt-BR when given 10.1', () => {
        const result = formatPrice(10.1);
       
        expect(result).toEqual('10,10');
      });
  });
  
  
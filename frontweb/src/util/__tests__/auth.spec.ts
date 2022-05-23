import { hasAnyRoles } from "util/auth";
import * as TokenModule from "../token";


describe('HasAnyRoles tests ', () => {
 
    test('should return true when emputy list', () => {
        const result = hasAnyRoles([])       
        expect(result).toEqual(true);
      });

      
    test('should return true when user has given role', () => {
        jest.spyOn(TokenModule,'getTokenData').mockReturnValue({
        exp: 0,
        user_name: 'string',
        authorities:['ROLE_ADMIN',  "ROLE_OPERATOR"]
        })
        const result = hasAnyRoles(['ROLE_ADMIN'])       
        expect(result).toEqual(true);
      });
  });
  
  
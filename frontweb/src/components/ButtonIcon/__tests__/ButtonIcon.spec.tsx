import { render,screen } from "@testing-library/react";
import ButtonIcon from "..";


test('ButtonIcon  should render butoon with given text ', () => {
 const text = "fazer login";

 render(
 <ButtonIcon text={text}/>
 );
    
 expect(screen.getByText(text)).toBeInTheDocument();

      
  });
  
  
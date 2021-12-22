import './styles.css';
import { Link } from 'react-router-dom';
import { ReactComponent as ArrowIcon } from 'assets/images/arrow.svg';
import ProductPrice from 'components/ProductPrice';


const ProductDetails = () => {
  return (
    <div className="product-details-container">
      <div className="base-card product-details-card">
        <Link to="/products">
          <div className="goback-container">
            <ArrowIcon />
            <h2>VOLTAR</h2>
          </div>
        </Link>
        <div className="row">
          <div className="col-xl-6">
            <div className="img-container">
              <img
                src="https://raw.githubusercontent.com/devsuperior/dscatalog-resources/master/backend/img/2-big.jpg"
                alt="NOME PROD"
              />
            </div>
            <div className="name-price-container">
              <h1>NOME PROD</h1>
              <ProductPrice price= {23456.67}/>
            </div>
          </div>
          <div className="col-xl-6">
            <div className="description-container">
              <h2>DESCRICAO PROD</h2>
              <p>Lorem ipsum, dolor sit amet consectetur adipisicing elit. Possimus cum aliquid, illo eum optio, libero officia tenetur dolorum ut expedita, similique aliquam consectetur quos ullam! Ducimus quasi repudiandae porro nemo!</p>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default ProductDetails;

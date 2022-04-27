import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import { useSelector, useDispatch } from 'react-redux';
import { detailsProduct } from '../store/actions/productActions';

function ProductPage(props) {
  const [qty, setQty] = useState(1);
  const userSignin = useSelector((state) => state.userSignin);
  const productDetails = useSelector((state) => state.productDetails);
  const { product, loading, error } = productDetails;
  const dispatch = useDispatch();
  const { userInfo } = userSignin;

  useEffect(() => {
    dispatch(detailsProduct(props.match.params.id));
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  const handleAddToCart = () => {
    // props.history.push('/cart/' + props.match.params.id + '?qty=' + qty);
    if(!userInfo)
      props.history.push("/signin?redirect=product/" + props.match.params.id);
  };

  return (
    <div>
      <div className="back-to-result">
        <Link to="/">Back to result</Link>
      </div>
      {loading ? (
        <div>Loading...</div>
      ) : error ? (
        <div>{error} </div>
      ) : (
        <>
          <div className="details">
            <div className="details-image">
              <img src={product.imageUrl} alt="product"></img>
            </div>
            <div className="details-info">
              <ul>
                <li>
                  <h4>{product.name}</h4>
                </li>
                <li>
                  Price: <b>LKR {product.price}</b>
                </li>
                <li>
                  Shop: {product?.producer?.name}
                </li>
                <li>
                  Tel: {product?.producer?.contactNo}
                </li>
              </ul>
            </div>
            <div className="details-action">
              <ul>
                <li>LKR {product.price}</li>
                <li>
                    <button
                      onClick={handleAddToCart}
                      className="button primary"
                    >
                      Add to Cart
                    </button>
                </li>
              </ul>
            </div>
          </div>
        </>
      )}
    </div>
  );
}
export default ProductPage;

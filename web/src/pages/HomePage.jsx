import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import { useSelector, useDispatch } from 'react-redux';
import { listProducts } from '../store/actions/productActions';
import Skeleton from "react-loading-skeleton";

export function ProductViewSkeleton() {
  return (
      <div className="card card-body">
        <Skeleton height={200}/>
        <Skeleton height={30}/>
        <div className="row">
          <div className="col-10">
            <Skeleton height={30}/>
          </div>
        </div>
        <div className="row">
          <div className="col-4">
            <Skeleton height={30}/>
          </div>
          <div className="col-4">
            <Skeleton height={30}/>
          </div>
        </div>
      </div>
  )
}

function HomePage(props) {
  const [searchKeyword, setSearchKeyword] = useState('');
  const category = props.match.params.id ? props.match.params.id : '';
  const productList = useSelector((state) => state.productList);
  const { products, loading, error } = productList;
  const dispatch = useDispatch();
  useEffect(() => {
    dispatch(listProducts(!!category ? category : '', searchKeyword));
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [category]);

  const submitHandler = (e) => {
    e.preventDefault();
    dispatch(listProducts(!!category ? category : '', searchKeyword));
  };

  return (
    <>
      <br/>
      {category && <h2>{category}</h2>}

      <ul className="filter">
        <li>
          <form onSubmit={submitHandler}>
            <input
              name="searchKeyword"
              placeholder={"Search foods ..."}
              onChange={(e) => setSearchKeyword(e.target.value)}
            />
            <button type="submit">Search</button>
          </form>
        </li>
        <li>
          {products?.length || 0} items
        </li>
      </ul>
      {loading ? (
          [...Array(20)].map((e, i) => <ProductViewSkeleton key={i}/>)
      ) : error ? (
        <div>{error}</div>
      ) : (
          <>
        <ul className="products">
          {products.map((product) => (
            <li key={product.id}>
              <div className="card card-body">
                <img style={{display: "block", margin: "0 auto 10px", maxHeight: "200px"}} className="img-fluid"
                     src={product.imageUrl} alt={product.name}/>
                <p><Link to={'/product/' + product.id}>{product.name}</Link></p>
                <h3 className="text-left">{`LKR ${product.price}`}</h3>
                <div className="text-right">
                  {product.producer.name}
                </div>
              </div>
            </li>
          ))}
        </ul>
          </>
      )}
    </>
  );
}
export default HomePage;

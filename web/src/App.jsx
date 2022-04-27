import React, {useEffect} from 'react';
import { BrowserRouter, Route, Link } from 'react-router-dom';
import './App.css';
import HomePage from './pages/HomePage';
import ProductPage from './pages/ProductPage';
import CartPage from './pages/CartPage';
import SignInPage from './pages/SignInPage';
import {useDispatch, useSelector} from 'react-redux';
import RegisterPage from './pages/RegisterPage';
import ProfilePage from './pages/ProfilePage';
import {listCategories} from "./store/actions/foodCategoriesActions";
import MyCartsPage from "./pages/MyCarts";

function App() {
  const userSignin = useSelector((state) => state.userSignin);
  const { userInfo } = userSignin;
  const dispatch = useDispatch();
  const categories = useSelector((state) => state.categories);

  useEffect(() => {
    dispatch(listCategories());
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  const openMenu = () => {
    document.querySelector('.sidebar').classList.add('open');
  };
  const closeMenu = () => {
    document.querySelector('.sidebar').classList.remove('open');
  };
  return (
    <BrowserRouter>
      <div className="grid-container">
        <header className="header">
          <div className="brand">
            <button onClick={openMenu}>&#9776;</button>
            <Link to="/">Fresh Foods</Link>
          </div>
          <div className="header-links">
            {userInfo ? (
                <>
                  <Link to="/carts">My Carts</Link>
                  <Link to="/profile">{userInfo.username}</Link>
                </>
            ) : (
              <Link to="/signin">Sign In</Link>
            )}
          </div>
        </header>
        <aside className="sidebar">
          <br/>
          <h3>Food Categories</h3>
          <button className="sidebar-close-button" onClick={closeMenu}>
            x
          </button>
          <ul className="categories">
            {
              !!categories && categories.categories.map(
                  (category, index) => {
                    return (
                        <li key={"category-link-" + index}>
                          <Link to={"/category/" + category} id={"category-link-" + index}>{category}</Link>
                        </li>
                    )
                  }
              )
            }
          </ul>
        </aside>
        <main className="main">
          <div className="content">
            <Route path="/profile" component={ProfilePage} />
            <Route path="/carts" component={MyCartsPage} />
            <Route path="/signin" component={SignInPage} />
            <Route path="/register" component={RegisterPage} />
            <Route path="/product/:id" component={ProductPage} />
            <Route path="/cart/:id?" component={CartPage} />
            <Route path="/category/:id" component={HomePage} />
            <Route path="/" exact={true} component={HomePage} />
          </div>
        </main>
        <footer className="footer">Copyright © 2022 Fresh Foods</footer>
      </div>
    </BrowserRouter>
  );
}

export default App;

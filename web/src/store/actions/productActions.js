import {
    PRODUCT_LIST_REQUEST,
    PRODUCT_LIST_SUCCESS,
    PRODUCT_LIST_FAIL,
    PRODUCT_DETAILS_REQUEST,
    PRODUCT_DETAILS_SUCCESS,
    PRODUCT_DETAILS_FAIL,
    BASE_URL,
} from "../../constants";
import axios from 'axios';

const listProducts = (
    category = '',
  searchKeyword = ''
) => async (dispatch) => {
  try {
    dispatch({ type: PRODUCT_LIST_REQUEST });
    const { data } = await axios.get(
      `${BASE_URL}/api/v1/food/search?category=${category}&name=${searchKeyword}`
    );
    dispatch({ type: PRODUCT_LIST_SUCCESS, payload: data.body });
  } catch (error) {
    dispatch({ type: PRODUCT_LIST_FAIL, payload: error.message });
  }
};

const detailsProduct = (productId) => async (dispatch) => {
  try {
      console.log("calling ...")
    dispatch({ type: PRODUCT_DETAILS_REQUEST, payload: productId });
    const { data } = await axios.get(`${BASE_URL}/api/v1/food/${productId}`);
    dispatch({ type: PRODUCT_DETAILS_SUCCESS, payload: data.body });
  } catch (error) {
    dispatch({ type: PRODUCT_DETAILS_FAIL, payload: error.message });
  }
};

export {
  listProducts,
  detailsProduct
};

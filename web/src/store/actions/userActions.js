import axios from "axios";
import Cookie from 'js-cookie';
import {
    USER_SIGNIN_REQUEST,
    USER_SIGNIN_SUCCESS,
    USER_SIGNIN_FAIL,
    USER_REGISTER_REQUEST,
    USER_REGISTER_SUCCESS,
    USER_REGISTER_FAIL,
    USER_LOGOUT,
    USER_UPDATE_REQUEST,
    USER_UPDATE_SUCCESS,
    USER_UPDATE_FAIL,
    BASE_URL, USER_DETAILS_REQUEST, USER_DETAILS_SUCCESS, USER_DETAILS_FAIL
} from "../../constants";

const update = ({ userId, name, username, password }) => async (dispatch, getState) => {
  const { userSignin: { userInfo } } = getState();
  dispatch({ type: USER_UPDATE_REQUEST, payload: { userId, name, username, password } });
  try {
    const { data } = await axios.put(`${BASE_URL}/api/v1/user`,
      { userId, name, username, password }, {
      headers: {
        Authorization: 'Bearer ' + userInfo.token
      }
    });
    dispatch({ type: USER_UPDATE_SUCCESS, payload: data });
    Cookie.set('userInfo', JSON.stringify(data));
  } catch (error) {
    dispatch({ type: USER_UPDATE_FAIL, payload: error.message });
  }
}

const signin = (username, password) => async (dispatch) => {
  dispatch({ type: USER_SIGNIN_REQUEST, payload: { username, password } });
  try {
    const { data } = await axios.post(`${BASE_URL}/api/v1/auth/signin`, { username, password });
    dispatch({ type: USER_SIGNIN_SUCCESS, payload: data });
    Cookie.set('userInfo', JSON.stringify(data));
  } catch (error) {
    dispatch({ type: USER_SIGNIN_FAIL, payload: "Login Failed!" });
  }
}

const register = (name, username, password) => async (dispatch) => {
  dispatch({ type: USER_REGISTER_REQUEST, payload: { name, username, password } });
  try {
    const { data } = await axios.post(`${BASE_URL}/api/v1/auth/signup`, { name, username, password });
    dispatch({ type: USER_REGISTER_SUCCESS, payload: data });
    Cookie.set('userInfo', JSON.stringify(data));
  } catch (error) {
    dispatch({ type: USER_REGISTER_FAIL, payload: error.message });
  }
}

const userProfile = () => async (dispatch, getState) => {
    const { userSignin: { userInfo } } = getState();
    dispatch({ type: USER_DETAILS_REQUEST });
    try {
        const { data } = await axios.get(`${BASE_URL}/api/v1/user/${userInfo.id}`, {
                headers: {
                    Authorization: userInfo.tokenType + ' ' + userInfo.accessToken
                }
            });
        dispatch({ type: USER_DETAILS_SUCCESS, payload: data.body });
    } catch (error) {
        dispatch({ type: USER_DETAILS_FAIL, payload: error.message });
    }
}

const logout = () => (dispatch) => {
  Cookie.remove("userInfo");
  dispatch({ type: USER_LOGOUT })
}
export { signin, register, logout, update, userProfile };
import {
    BASE_URL, CATEGORIES_LIST_FAIl, CATEGORIES_LIST_REQUEST, CATEGORIES_LIST_SUCCESS
} from "../../constants";
import axios from "axios";

const listCategories = () => async (dispatch) => {
    try {
        dispatch({ type: CATEGORIES_LIST_REQUEST });
        const { data } = await axios.get(
            `${BASE_URL}/api/v1/food/category/list`
        );
        dispatch({ type: CATEGORIES_LIST_SUCCESS, payload: data.body });
    } catch (error) {
        dispatch({ type: CATEGORIES_LIST_FAIl, payload: error.message });
    }
};

export {listCategories};
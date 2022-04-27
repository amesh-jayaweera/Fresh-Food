import React, { useState, useEffect } from 'react';
import {logout, update, userProfile} from '../store/actions/userActions';
import { useDispatch, useSelector } from 'react-redux';

function ProfilePage(props) {
  const [name, setName] = useState('');
  const [password, setPassword] = useState('');
  const [username, setUsername] = useState('');
  const dispatch = useDispatch();

  const user = useSelector(state => state.userDetails);
  const { userDetails } = user;
  const handleLogout = () => {
    dispatch(logout());
    props.history.push("/signin");
  }
  const submitHandler = (e) => {
    e.preventDefault();
    dispatch(update({ userId: userDetails.id, username, name, password }))
  }
  const userUpdate = useSelector(state => state.userUpdate);
  const { loading, success, error } = userUpdate;

  useEffect(() => {
    if (userDetails) {
      setUsername(userDetails.username);
      setName(userDetails.name);
      setPassword(userDetails.password);
    }
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [userDetails])

  useEffect(()=> {
    dispatch(userProfile());
  },[])

  return <div className="profile">
    <div className="profile-info">
      <div className="form">
        <form onSubmit={submitHandler} >
          <ul className="form-container">
            <li>
              <h2>User Profile</h2>
            </li>
            <li>
              {loading && <div>Loading...</div>}
              {error && <div>{error}</div>}
              {success && <div>Profile Saved Successfully.</div>}
            </li>
            <li>
              <label htmlFor="name">
                Name
          </label>
              <input value={name} type="name" name="name" id="name" onChange={(e) => setName(e.target.value)}>
              </input>
            </li>
            <li>
              <label htmlFor="username">
                Username
          </label>
              <input value={username} type="text" name="username" id="username" onChange={(e) => setUsername(e.target.value)}>
              </input>
            </li>
            <li>
              <label htmlFor="password">Password</label>
              <input value={password} type="password" id="password" name="password" onChange={(e) => setPassword(e.target.value)}>
              </input>
            </li>

            <li>
              <button type="submit" className="button primary">Update</button>
            </li>
            <li>
              <button type="button" onClick={handleLogout} className="button secondary full-width">Logout</button>
            </li>

          </ul>
        </form>
      </div>
    </div>
  </div>
}

export default ProfilePage;

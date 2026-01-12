import { BrowserRouter, Route, Routes } from "react-router-dom";
import { Container } from "react-bootstrap";
import { HealthProfileContext, HealthProfileDispatchContext, MyDispatchContext, MyUserContext } from "./configs/MyContexts";
import { useReducer } from "react";
import 'bootstrap/dist/css/bootstrap.min.css';
import MyUserReducer from "./reducers/MyUserReducer";
import HealthProfileReducer from "./reducers/HealthProfileReducer";
import Header from "./components/layout/Header";
import Footer from "./components/layout/Footer";

import Home from "./components/Home";
import Register from "./components/Register";
import Login from "./components/Login";

import Profile from "./components/Profile";
import UpdateProfile from './components/UpdateProfile';

import HealthProfile from './components/HealthProfile';
import AddHealthProfile from './components/AddHealthProfile';
import UpdateHealthProfile from './components/UpdateHealthProfile';

import Statistics from './components/Statistics';
import ComingSoon from './components/ComingSoon';


const App = () => {
  const [user, dispatch] = useReducer(MyUserReducer, null);
  const [health_profile, healthProfiledispatch] = useReducer(HealthProfileReducer, null);

  return (
    <MyUserContext.Provider value={user}>
      <MyDispatchContext.Provider value={dispatch}>
        <HealthProfileContext.Provider value={health_profile}>
          <HealthProfileDispatchContext.Provider value={healthProfiledispatch}>
            <BrowserRouter>
              <Header />

              <Container>
                <Routes>
                  
                  <Route path="/" element={<Home />} />
                  <Route path="/login" element={<Login />} />
                  <Route path="/register" element={<Register />} />

                 
                  <Route path="/profile" element={<Profile />} />
                  <Route path="/update_profile" element={<UpdateProfile />} />

                  
                  <Route path="/health_profile" element={<HealthProfile />} />
                  <Route path="/add_health_profile" element={<AddHealthProfile />} />
                  <Route path="/update_health_profile" element={<UpdateHealthProfile/>} />

                 
                  <Route path="/training_packages" element={<ComingSoon title="Các bài tập" />} />
                  <Route path="/user_trainer" element={<ComingSoon title="HLV của tôi" />} />
                  <Route path="/feedbacks" element={<ComingSoon title="Bình luận" />} />
                  
                  <Route path="/statistics" element={<Statistics />} />
                  <Route path="/request_user_trainer" element={<ComingSoon title="Yêu cầu thành viên" />} />
                  <Route path="/trainer_statistics" element={<ComingSoon title="Thống kê HLV" />} />
                  <Route path="/messenger" element={<ComingSoon title="Nhắn tin" />} />
                  <Route path="/admin/accounts" element={<ComingSoon title="Quản lý tài khoản (Admin)" />} />



                </Routes>
              </Container>

              <Footer />
            </BrowserRouter>
          </HealthProfileDispatchContext.Provider>
        </HealthProfileContext.Provider>
      </MyDispatchContext.Provider>
    </MyUserContext.Provider>
  );
}

export default App;
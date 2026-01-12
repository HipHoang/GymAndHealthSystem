import { Container, Row, Col } from "react-bootstrap";
import { Link } from "react-router-dom";

const Footer = () => {
    return (
        <footer className="bg-dark text-white mt-5 py-4">
            <Container>
                <Row>
                    <Col md={4}>
                        <h5>Về chúng tôi</h5>
                        <p>Hệ thống hỗ trợ quản lý phòng gym, theo dõi sức khỏe và tiến trình tập luyện toàn diện.</p>
                    </Col>
                    <Col md={4}>
                        <h5>Liên kết nhanh</h5>
                        <ul className="list-unstyled">
                            <li><Link to="/" className="text-white text-decoration-none">Trang chủ</Link></li>
                            <li><Link to="/sanpham" className="text-white text-decoration-none">Sản phẩm</Link></li>
                            <li><Link to="/lienhe" className="text-white text-decoration-none">Liên hệ</Link></li>
                        </ul>
                    </Col>
                    <Col md={4}>
                        <h5>Liên hệ</h5>
                        <p>Email: support@hmh.vn</p>
                        <p>Hotline: 0123 456 789</p>
                        <p>Địa chỉ: 123 Nha Be Road, TP.HCM</p>
                    </Col>
                </Row>
                <hr className="border-light" />
                <p className="text-center mb-0">© 2025 HMH. All rights reserved.</p>
            </Container>
        </footer>
    );
}

export default Footer;
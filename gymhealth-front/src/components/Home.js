import { Container, Row, Col, Button, Card } from "react-bootstrap";
import { MyUserContext } from "../configs/MyContexts";
import { useContext } from "react";
const Home = () => {
const user = useContext(MyUserContext);
    return (
        <>
            <Container className="mt-5 mb-5">
                <Row className="align-items-center">
                    <Col md={6}>
                        <h1 className="text-success mb-4">Chào mừng đến với <strong>Hệ thống Gym & Health</strong>!</h1>
                        <p>
                            Nền tảng toàn diện giúp bạn quản lý tập luyện, theo dõi sức khỏe và kết nối với huấn luyện viên cá nhân.
                            Dành cho hội viên, PT và quản lý phòng gym.
                        </p>
                        {user === null && (
                            <>
                                <Button href="/register" variant="success" className="me-2">
                                    Đăng ký
                                </Button>
                                <Button href="/login" variant="outline-success">
                                    Đăng nhập
                                </Button>
                            </>
                        )}
                    </Col>
                    <Col md={6}>
                        <img
                            src="https://res.cloudinary.com/dy9g3l14t/image/upload/v1754599248/hjbomiwzflmvpwdwvjxm.jpg"
                            alt="Gym Health System"
                            className="img-fluid"
                        />
                    </Col>
                </Row>
            </Container>

            <Container className="mb-5">
                <h2 className="text-center text-primary mb-4">💪 Tính năng nổi bật</h2>
                <Row>
                    {[
                        {
                            title: "Quản lý hồ sơ sức khỏe",
                            desc: "Theo dõi chỉ số cơ thể (BMI, chiều cao, cân nặng, giới tính,...) và lịch sử sức khỏe."
                        },
                        {
                            title: "Gói tập luyện & dinh dưỡng",
                            desc: "Đăng ký và theo dõi các gói tập luyện phù hợp từng mục tiêu cá nhân."
                        },
                        {
                            title: "Đặt lịch & quản lý lịch tập",
                            desc: "Đặt lịch tập với huấn luyện viên cá nhân (PT), nhận nhắc nhở thông minh."
                        },
                        {
                            title: "Theo dõi tiến độ tập luyện",
                            desc: "Xem biểu đồ tiến độ, mức độ hoàn thành và hiệu quả tập luyện hàng tuần."
                        },
                        {
                            title: "Tương tác với huấn luyện viên",
                            desc: "Gửi tin nhắn, nhận phản hồi và lời khuyên từ PT."
                        },
                        {
                            title: "Phân quyền & bảo mật",
                            desc: "Hệ thống phân quyền rõ ràng cho Hội viên, PT, Quản lý. Bảo mật thông tin người dùng."
                        }
                    ].map((feature, idx) => (
                        <Col md={4} key={idx} className="mb-4">
                            <Card className="h-100 shadow-sm">
                                <Card.Body>
                                    <Card.Title className="text-success">{feature.title}</Card.Title>
                                    <Card.Text>{feature.desc}</Card.Text>
                                </Card.Body>
                            </Card>
                        </Col>
                    ))}
                </Row>
            </Container>

            <Container className="mb-5">
                <h2 className="text-center text-warning mb-4">🗣️ Phản hồi từ người dùng</h2>
                <Row>
                    {[
                        {
                            name: "Nguyễn Văn Tâm",
                            comment: "Tôi có thể đặt lịch tập và theo dõi tiến độ rất dễ dàng. Rất phù hợp với người mới tập.",
                            avatar: "https://i.pravatar.cc/100?img=1"
                        },
                        {
                            name: "Trần Thị Mai",
                            comment: "Là huấn luyện viên, tôi thấy hệ thống giúp tôi theo dõi học viên hiệu quả hơn.",
                            avatar: "https://i.pravatar.cc/100?img=2"
                        },
                        {
                            name: "Lê Quốc Khánh",
                            comment: "Ứng dụng hỗ trợ tốt cho việc quản lý hội viên và gói tập. Tôi rất hài lòng.",
                            avatar: "https://i.pravatar.cc/100?img=3"
                        }
                    ].map((fb, idx) => (
                        <Col md={4} key={idx} className="mb-4">
                            <Card className="text-center h-100 shadow">
                                <Card.Img variant="top" src={fb.avatar} className="rounded-circle w-25 mx-auto mt-3" />
                                <Card.Body>
                                    <Card.Title>{fb.name}</Card.Title>
                                    <Card.Text>"{fb.comment}"</Card.Text>
                                </Card.Body>
                            </Card>
                        </Col>
                    ))}
                </Row>
            </Container>

            <Container className="text-center mb-5">
                <h2 className="text-danger mb-3">Bắt đầu hành trình khỏe mạnh hôm nay</h2>
                <p>Tham gia để quản lý lịch tập, theo dõi sức khỏe và kết nối với chuyên gia.</p>
                <Button href="/register" variant="danger" size="lg">Tham gia ngay</Button>
            </Container>
        </>
    );
};

export default Home;

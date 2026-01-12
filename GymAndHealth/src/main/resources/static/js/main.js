function confirmAndDelete(endpoint, id, entityName = "dữ liệu") {
    if (confirm(`Bạn có chắc chắn muốn xoá ${entityName} này không?`)) {
        fetch(endpoint + id, {
            method: "DELETE"
        }).then(res => {
            if (res.status === 204) {
                alert(`${entityName} đã xoá thành công!`);
                location.reload();
            } else {
                alert(`Xoá ${entityName} thất bại!`);
            }
        });
    }
}

function deleteUser(endpoint, id) {
    confirmAndDelete(endpoint, id, "người dùng");
}

function deleteHealthProfile(endpoint, id) {
    confirmAndDelete(endpoint, id, "hồ sơ sức khoẻ");
}

function deleteTrainingPackage(endpoint, id) {
    confirmAndDelete(endpoint, id, "gói tập");
}

function deleteUserPackage(endpoint, id) {
    confirmAndDelete(endpoint, id, "gói đã mua");
}

function deleteBookingSchedule(endpoint, id) {
    confirmAndDelete(endpoint, id, "lịch tập");
}

function deleteFeedback(endpoint, id) {
    confirmAndDelete(endpoint, id, "đánh giá");
}

function deleteTrainingProgress(endpoint, id) {
    confirmAndDelete(endpoint, id, "tiến độ tập");
}

function deletePaymentTransaction(endpoint, id) {
    confirmAndDelete(endpoint, id, "giao dịch");
}

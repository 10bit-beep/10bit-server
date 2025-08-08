document.getElementById("signup-btn").onclick = async function () {
    const studentNumber = Number(document.getElementById("studentNumber").value.trim());
    // const name = document.getElementById("name").value.trim();
    const publicId = document.getElementById("username").value.trim();
    const password = document.getElementById("password").value.trim();
    const email = document.getElementById("email").value.trim();

    if (!publicId || !password || !studentNumber || !email || !name) {
        alert("모든 항목을 입력해주세요.");
        return;
    }

    try {
        const res = await fetch("http://localhost:3000/signup", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({
                publicId: userid,
                password: password,
                studentNumber: studentNumber,
                email: email
            })
        });

        const data = await res.json();

        if (res.ok && data.success) {
            alert("회원가입 성공!");
            window.location.href = "../login/login.html";
        } else {
            alert("회원가입 실패: " + (data.message || "알 수 없는 오류"));
        }

    } catch (err) {
        console.error("회원가입 오류:", err);
        alert("서버에 연결할 수 없습니다.");
    }
};

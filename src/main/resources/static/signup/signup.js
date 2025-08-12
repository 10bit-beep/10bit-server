document.addEventListener('DOMContentLoaded', function () {
    const signupForm = document.querySelector('form');

    signupForm.addEventListener('submit', async function (e) {
        e.preventDefault();

        const publicId = document.getElementById("userid").value.trim();
        const password = document.getElementById("password").value.trim();
        const studentNumber = Number(document.getElementById("student").value.trim());
        const email = document.getElementById("email").value.trim();

        if (!publicId || !password || !studentNumber || !email) {
            alert("모든 항목을 입력해주세요.");
            return;
        }

        try {
            const res = await fetch("http://localhost:8080/auth/signup", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({
                    publicId, password, studentNumber, email
                })
            });

            if (!res.ok) {
                const errorText = await res.text();
                alert("회원가입 실패: " + errorText);
                return;
            }

            const data = await res.json();
            if (data.success) {
                alert("회원가입 성공!");
                window.location.href = "../login/login.html";
            } else {
                alert("회원가입 실패: " + (data.message || "알 수 없는 오류"));
            }
        } catch (err) {
            console.error("회원가입 오류:", err.message);
            alert("서버에 연결할 수 없습니다: " + err.message);
        }

    });
});

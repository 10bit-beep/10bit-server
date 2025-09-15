document.addEventListener('DOMContentLoaded', function () {
    const loginForm = document.querySelector('form');

    loginForm.addEventListener('submit', async function (e) {
        e.preventDefault();

        const userid = document.getElementById('userid').value.trim();
        const password = document.getElementById('password').value.trim();

        if (!userid || !password) {
            alert('아이디와 비밀번호를 모두 입력해주세요.');
            return;
        }

        try {
            const res = await fetch('/auth/login', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'X-Platform': 'WEB',
                    'userAgent': navigator.userAgent
                },
                body: JSON.stringify({
                    publicId: userid,
                    password: password
                }),
            });

            if (res.ok) {
                const token = await res.text();
                alert(`환영합니다, ${userid}님!`);
                alert("로그인 성공! 토큰: " + token);
                // 토큰 저장 (필요시)
                // localStorage.setItem('token', token);
                window.location.href = '../main/main.html';
            } else {
                alert("로그인 실패");
            }
        } catch (err) {
            console.error('로그인 오류:', err);
            alert('서버에 연결할 수 없습니다.');
        }
    });
});
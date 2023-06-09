<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="uk">
<head>
    <%@include file="../../views/layouts/head.jsp"%>
</head>
<body>
<header>
    <%@include file="../../views/layouts/nav.jsp"%>
</header>
<link rel="stylesheet" href="static/styles/contacts.css">
<main>
    <div class="contacts-box">
        <div class="content-box justify-content-between">
            <div class="w-100">
                <div class="fs-2 text-light text-center mb-4">Залишайтеся на звʼязку з нами!</div>
                <div class="d-flex justify-content-around mb-4">
                    <div class="text-light">
                        <div>Україна, Київ</div>
                        <div>Хрещатик 42</div>
                    </div>
                    <div class="text-light">
                        <div>Звʼяжіться з нами легко!</div>
                        <div>contactcenter@whcompany.com</div>
                    </div>
                </div>
            </div>
            <div class="form-contact">
                <form method="post" action="Email">
                    <div class="form-floating mb-3">
                        <input type="text" class="form-control" id="name" name="name" required>
                        <label for="name">Імʼя</label>
                    </div>
                    <div class="form-floating mb-3">
                        <input type="text" class="form-control" id="surname" name="surname" required>
                        <label for="surname">Фамілія</label>
                    </div>
                    <div class="form-floating mb-3">
                        <input type="email" class="form-control" id="email" name="email" required>
                        <label for="email">Ваша поштова адреса</label>
                    </div>
                    <div class="form-floating">
                        <textarea id="message" class="form-control" aria-label="message" name="message" required>
                        </textarea>
                        <label for="message">Ваше повідомлення</label>
                    </div>
                    <div class="d-flex justify-content-center mt-3">
                        <button type="submit" class="btn btn-secondary">Надіслати лист</button>
                    </div>
                </form>
            </div>
            <div class="d-flex ico-block">
                <div>
                    <img src="./static/img/twitter.png" class="contact-ico me-3">
                </div>
                <div>
                    <img src="./static/img/instagram.png" class="contact-ico me-3">
                </div>
                <div>
                    <img src="./static/img/facebook.png" class="contact-ico">
                </div>
            </div>
        </div>
        <div>
            <img src="static/img/contacts.jpg" class="contacts-img">
        </div>
    </div>
</main>
<footer class="text-center">
    <%@include file="../../views/layouts/footer.jsp"%>
</footer>
</body>
</html>


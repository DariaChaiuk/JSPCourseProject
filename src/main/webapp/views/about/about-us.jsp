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
<link rel="stylesheet" href="static/styles/about.css">
<main>
    <div class="about-box">
        <div class="p-4 content-box text-light">
            <div class="text-center fs-2 mb-3">Реалізуйте свої мрії з нами!</div>
            <div class="w-50 fs-4 mt-3 about-text">
                Ми ріелторська агенція з великою базою пропозицій. Кожного дня ми працюємо щоб допомогти вам знайти житло вашої мрії.
                Ми розуміємо як якість та комфорт важливі для наших клієнтів, тож ми маємо пропозиції тільки найвищої якості.
                Наші спеціалісти готові відповісти на всі ваші питання протягом нашого робочого часу.
                Ви можете переглянути усі наші пропозиції на нашому порталі і звʼязатися з нами через нашу сторінку
                <a href="./Contacts" class="page-ref">контактів</a> або безпосередньо через будь-яку пропозицію!
            </div>
        </div>
        <div>
            <img src="static/img/about.jpg" class="about-img">
        </div>
    </div>
</main>
<footer class="text-center">
    <%@include file="../../views/layouts/footer.jsp"%>
</footer>
</body>
</html>
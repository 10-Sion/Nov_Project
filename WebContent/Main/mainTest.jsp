<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="mainTest.css">
    <title>Notification</title>
</head>

<body>

    <div class="notification" id="note-1">
        <div class="notification__box">
            <div class="notification__content">
                <div class="notification__icon">
                    <svg class="notification__icon-svg" role="img" aria-label="success" width="32px" height="32px">
                        <use xlink:href="#success"></use>
                    </svg>
                </div>
                <div class="notification__text">
                    <div class="notification__text-title">Changes Saved</div>
                </div>
            </div>
            <div class="notification__btns">
                <button class="notification__btn" type="button" data-dismiss="note-1">
                    <span class="notification__btn-text">OK</span>
                </button>
            </div>
        </div>
    </div>

    <script src="mainTest.js"></script>
</body>

</html>

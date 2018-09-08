$('.account-setting').click(function () {
    $('.account-setting').addClass('active');
    $('.account-change-password').removeClass('active');
    $('.account-change-address').removeClass('active');
    $('.advice').removeClass('active');
    $('#line').addClass('one');
    $('#line').removeClass('two');
    $('#line').removeClass('three');
    $('#line').removeClass('four');

});
$('.account-change-password').click(function () {
    $('.account-setting').removeClass('active');
    $('.account-change-password').addClass('active');
    $('.account-change-address').removeClass('active');
    $('.advice').removeClass('active');
    $('#line').removeClass('one');
    $('#line').addClass('two');
    $('#line').removeClass('three');
    $('#line').removeClass('four');
});
$('.account-change-address').click(function () {
    $('.account-change-password').removeClass('active');
    $('.account-setting').removeClass('active');
    $('.account-change-address').addClass('active');
    $('.advice').removeClass('active');
    $('#line').removeClass('one');
    $('#line').removeClass('two');
    $('#line').addClass('three');
    $('#line').removeClass('four');
});

$('.advice').click(function () {
    $('.account-change-password').removeClass('active');
    $('.account-setting').removeClass('active');
    $('.account-change-address').removeClass('active');
    $('.advice').addClass('active');
    $('#line').removeClass('one');
    $('#line').removeClass('two');
    $('#line').removeClass('three');
    $('#line').addClass('four');
})

$('.account-setting').click(function () {
    $('#first').addClass('active');
    $('#second').removeClass('active');
    $('#third').removeClass('active');
    $('#fourth').removeClass('active');
});
$('.account-change-password').click(function () {
    $('#first').removeClass('active');
    $('#second').addClass('active');
    $('#third').removeClass('active')
    $('#fourth').removeClass('active');
});
$('.account-change-address').click(function () {
    $('#first').removeClass('active');
    $('#second').removeClass('active');
    $('#third').addClass('active')
    $('#fourth').removeClass('active');
});
$('.advice').click(function () {
    $('#first').removeClass('active');
    $('#second').removeClass('active');
    $('#third').removeClass('active')
    $('#fourth').addClass('active');
});
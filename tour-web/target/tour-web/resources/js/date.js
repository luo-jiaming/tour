$(function () {
    $('#date').dateRangePicker(
        {}).bind('datepicker-first-date-selected', function (event, obj) {
            /* This event will be triggered when first date is selected */
            console.log('first-date-selected', obj);
        })
        .bind('datepicker-change', function (event, obj) {
            /* This event will be triggered when second date is selected */
            console.log('change', obj);
        })
        .bind('datepicker-apply', function (event, obj) {
            /* This event will be triggered when user clicks on the apply button */
            console.log('apply', obj);
        })
        .bind('datepicker-close', function () {
            /* This event will be triggered before date range picker close animation */
            console.log('before close');
        })
        .bind('datepicker-closed', function () {
            /* This event will be triggered after date range picker close animation */
            console.log('after close');
        })
        .bind('datepicker-open', function () {
            /* This event will be triggered before date range picker open animation */
            console.log('before open');
        })
        .bind('datepicker-opened', function () {
            /* This event will be triggered after date range picker open animation */
            console.log('after open');
        });
});
// $('table').dataTable();
let theTable = $($('body table')[0]);

theTable.addClass('display compact dataTable');
theTable.attr('id', 'example_wrapper');
$(document).ready(function() {
    $('#example_wrapper').DataTable();
} );
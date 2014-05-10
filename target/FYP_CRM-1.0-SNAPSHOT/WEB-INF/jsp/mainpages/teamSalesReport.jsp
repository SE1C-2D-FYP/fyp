
<div id="dialog_salesReport_teamSalesReport" style="display:none;">
    <div class="box box-success" >
        <div class="box-header">
            <h3 class="box-title">Team Sales Report</h3>
        </div>
        <div class="box-body chart-responsive">
            <div class="pull-right box-tools">
                <!-- button with a dropdown -->
                <div class="btn-group">
                    <button class="btn btn-warning btn-sm dropdown-toggle" data-toggle="dropdown"><i class="fa fa-bars"></i></button>
                    <ul class="dropdown-menu pull-right" role="menu">
                        <li><a href="#" id="export_teamSalesReport_pdf">Export to PDF</a></li>
                    </ul>
                </div>
            </div>
            <div class="chart" id="salesReport_teamSalesReport_totalSalesAmount" style="height: 250px;"></div>
            <div class="chart" id="salesReport_teamSalesReport_totalSalesUnit" style="height: 250px"></div>

            <h5>Sales by Geographics</h5>
            <div class="chart" id="salesReport_teamSalesReport_salesByGeog" style="height: 250px"></div>
            <h5>Top Unit Sales</h5>
            <div class="chart" id="salesReport_teamSalesReport_topUnitSales" style="height: 250px"></div>
            <h5>Agent Performance (Sales)</h5>
            <div class="chart" id="salesReport_teamSalesReport_agentPerformanceSales" style="height: 250px"></div>
            <h5>Agent Performance (Unit)</h5>
            <div class="chart" id="salesReport_teamSalesReport_agentPerformanceUnit" style="height: 250px"></div>

        </div><!-- /.box-body -->
    </div><!-- /.box -->            
</div>


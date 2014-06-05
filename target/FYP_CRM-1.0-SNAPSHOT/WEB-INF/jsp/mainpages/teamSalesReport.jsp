
<div id="dialog_salesReport_teamSalesReport" style="display:none;">
    <div class="box box-success" >
        <div class="box-body chart-responsive">
            <div>
                <ul class="nav nav-tabs" id="teamSalesReport_tab">
                    <li class="active"><a href="#teamSalesReport_totalSales_panel" data-toggle="tab">Total Sales</a></li>
                    <li><a href="#teamSalesReport_geo_panel" data-toggle="tab">Geographical</a></li>
                    <li><a href="#teamSalesReport_topUnitSales_panel" data-toggle="tab">Top Unit Sales</a></li>
                    <li><a href="#teamSalesReport_agentPerformance_panel" data-toggle="tab">Agent Performance</a></li>
                    <div class="pull-right box-tools">
                        <!-- button with a dropdown -->
                        <div class="btn-group">
                            <button class="btn btn-warning btn-sm dropdown-toggle" data-toggle="dropdown"><i class="fa fa-bars"></i></button>
                            <ul class="dropdown-menu pull-right" role="menu">
                                <li><a href="#" id="export_teamSalesReport_pdf">Export to PDF</a></li>
                            </ul>
                        </div>
                    </div>
                </ul>
            </div>

            <div class="tab-content">
                <div class="tab-pane active" id="teamSalesReport_totalSales_panel">
                    <div class="chart" id="salesReport_teamSalesReport_totalSalesAmount" style="height: 250px;"></div>
                    <div class="chart" id="salesReport_teamSalesReport_totalSalesUnit" style="height: 250px"></div>
                </div>

                <div class="tab-pane" id="teamSalesReport_geo_panel">
                    <div class="chart" id="salesReport_teamSalesReport_salesByGeog" style="height: 250px"></div>
                </div>
                <div class="tab-pane" id="teamSalesReport_topUnitSales_panel">
                    <div class="chart" id="salesReport_teamSalesReport_topUnitSales" style="height: 250px"></div>
                </div>
                <div class="tab-pane" id="teamSalesReport_agentPerformance_panel">
                    <h5>Agent Performance (Sales)</h5>
                    <div class="chart" id="salesReport_teamSalesReport_agentPerformanceSales" style="height: 250px"></div>
                    <h5>Agent Performance (Unit)</h5>
                    <div class="chart" id="salesReport_teamSalesReport_agentPerformanceUnit" style="height: 250px"></div>
                </div>

            </div><!-- /.box-body -->
        </div><!-- /.box -->            
    </div>
</div>


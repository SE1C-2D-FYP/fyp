<!--<div class="col-xs-12 connectedSortable">
    <button id="showClientList" class="btn btn-primary">Show Client List</button>
</div> /.col -->

<div id="list" hidden="">
    <table class="table-hover" cellpadding="9" cellspacing="9" border="0" id="listClient">
        <thead>
            <tr>
                <th style="text-align: center;">Client ID</th>
                <th style="text-align: center;">Name</th>
                <th style="text-align: center;">Address</th>
                <th style="text-align: center;">Phone</th>
            </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
    <div class="connectedSortable">
        <button id="insertNewClient" class="btn btn-primary">Create New Client</button>
    </div><!-- /.col -->
</div>

<div class="box box-primary" hidden="" id="insertClientForm">
    <div class="box-header">
        <h3 class="box-title">New Client Form</h3>
    </div><!-- /.box-header -->
    <!-- form start -->
    <form id="insertClient" method="Post">
        <div class="box-body">
            <div class="form-group">
                <label>Client name</label>
                <input type="text" class="form-control" name="clientName" placeholder="Enter name">
            </div>
            <div class="form-group">
                <label>Address</label>
                <textarea class="form-control" rows="3" placeholder="Enter address" name="address"></textarea>
            </div>
            <div class="form-group">
                <label>Phone no.</label>
                <input type="text" class="form-control" name="phone" placeholder="Enter phone nubmer">
            </div>
            <div class="form-group">
                <label>Mobile Phone no.</label>
                <input type="text" class="form-control" name="mphone" placeholder="Enter mobile phone number">
            </div>
            <div class="form-group">
                <label>Fax no.</label>
                <input type="text" class="form-control" name="fax" placeholder="Enter fax number">
            </div>
            <div class="form-group">
                <label>Email address</label>
                <input type="text" class="form-control" name="email" placeholder="Enter email">
            </div>
        </div><!-- /.box-body -->

        <div class="box-footer">
            <button type="submit" class="btn btn-primary">Submit</button>
            <button type="reset" class="btn btn-primary">Reset</button>
        </div>
    </form>
</div><!-- /.box -->

<div class="box box-primary" hidden="" id="editClientForm">
    <div class="box-header">
        <h3 class="box-title">Edit Client</h3>
    </div><!-- /.box-header -->
    <!-- form start -->
    <form id="editClient" method="Post">
        <div class="box-body">
            <input type="hidden" class="form-control" name="clientId" />
            <input type="hidden" class="form-control" name="empId" />
            <div class="form-group">
                <label>Client name</label>
                <input type="text" class="form-control" name="editClientName" placeholder="Enter name">
            </div>
            <div class="form-group">
                <label>Address</label>
                <textarea class="form-control" rows="3" placeholder="Enter address" name="editAddress"></textarea>
            </div>
            <div class="form-group">
                <label>Phone no.</label>
                <input type="text" class="form-control" name="editPhone" placeholder="Enter phone nubmer">
            </div>
            <div class="form-group">
                <label>Mobile Phone no.</label>
                <input type="text" class="form-control" name="editMphone" placeholder="Enter mobile phone number">
            </div>
            <div class="form-group">
                <label>Fax no.</label>
                <input type="text" class="form-control" name="editFax" placeholder="Enter fax number">
            </div>
            <div class="form-group">
                <label>Email address</label>
                <input type="text" class="form-control" name="editEmail" placeholder="Enter email">
            </div>
        </div><!-- /.box-body -->

        <div class="box-footer">
            <button type="submit" class="btn btn-primary">Submit</button>
            <button type="reset" class="btn btn-primary">Reset</button>
        </div>
    </form>
</div><!-- /.box -->

<div class="box box-primary" hidden="" id="insertContactForm">
    <div class="box-header">
        <h3 class="box-title">New Contact</h3>
    </div><!-- /.box-header -->
    <!-- form start -->
    <form role="form" id="insertContact">
        <div class="box-body">
            <input type="hidden" name="ctClientId" />
            <div class="form-group">
                <label>Chinese name</label>
                <input type="text" class="form-control" name="chiName" placeholder="Enter Chinese name">
            </div>
            <div class="form-group">
                <label>English name</label>
                <input type="text" class="form-control" name="engName" placeholder="Enter English name">
            </div>
            <div class="form-group">
                <label>Phone no.</label>
                <select name="phoneType">
                    <option value="Home">Home</option>
                    <option value="Mobile">Mobile</option>
                    <option value="Office">Office</option>
                </select>
                <input type="text" class="form-control" name="phoneNo" placeholder="Enter phone nubmer">
            </div>
        </div><!-- /.box-body -->

        <div class="box-footer">
            <button type="submit" class="btn btn-primary">Submit</button>
            <button type="reset" class="btn btn-primary">Reset</button>
        </div>
    </form>
</div><!-- /.box -->

<div class="box box-info" hidden="" id="editContactForm">
    <div class="box-header">
        <h3 class="box-title">Edit Contact</h3>
    </div><!-- /.box-header -->
    <!-- form start -->
    <form role="form" id="editContact">
        <div class="box-body">
            <div class="form-group">
                <label>Chinese name</label>
                <input type="text" class="form-control" name="editChiName">
            </div>
            <div class="form-group">
                <label>English name</label>
                <input type="text" class="form-control" name="editEngName">
            </div>
            <div class="form-group">
                <label>Phone no.</label>
                <select name="editPhoneType">
                    <option value="Home">Home</option>
                    <option value="Mobile">Mobile</option>
                    <option value="Office">Office</option>
                </select>
                <input type="text" class="form-control" name="editPhoneNo" placeholder="Enter phone nubmer">
            </div>
        </div><!-- /.box-body -->

        <div class="box-footer">
            <button type="submit" class="btn btn-primary">Submit</button>
            <button type="reset" class="btn btn-primary">Reset</button>
        </div>
    </form>
</div><!-- /.box -->

<div class="box box-primary" hidden="" id="insertTransactionInfoForm">
    <div class="box-header">
        <h3 class="box-title">New Transaction Form</h3>
    </div><!-- /.box-header -->
    <!-- form start -->
    <form id="insertTransactionInfo">
        <div class="box-body">
            <div class="form-group">
                <label>Date</label>
                <input id="insertTransactionInfoForm_transactionDate" type="date" name="transactionDate" placeholder="Enter Date"/>
            </div>
            <div class="form-group">
                <label>Address</label>
                <div class="ui-widget">
                    <label for="autocompleteTransactionAddressEstate">Estate: </label>
                    <input id="autocompleteTransactionAddressEstate" name="transactionAddressEstate" placeholder="Enter Estate" />
                    <input type="hidden" id="autocompleteTransactionAddressEstate_hidden" name="autocompleteTransactionAddressEstate_hidden" />
                </div>
                <div class="ui-widget">
                    <label for="autocompleteTransactionAddressBlock">Block: </label>
                    <input id="autocompleteTransactionAddressBlock" name="transactionAddressBlock" placeholder="Enter Block" />
                    <input type="hidden" id="autocompleteTransactionAddressBlock_hidden" name="autocompleteTransactionAddressBlock_hidden" />
                </div>
                <div class="ui-widget">
                    <label for="autocompleteTransactionAddressFloor">Floor: </label>
                    <input id="autocompleteTransactionAddressFloor" name="transactionAddressFloor" placeholder="Enter Floor" />
                    <input type="hidden" id="autocompleteTransactionAddressFloor_hidden" name="autocompleteTransactionAddressFloor_hidden" />
                </div>
                <div class="ui-widget">
                    <label for="autocompleteTransactionAddressFlat">Flat: </label>
                    <input id="autocompleteTransactionAddressFlat" name="transactionAddressFlat" placeholder="Enter Flat" />
                    <input type="hidden" id="autocompleteTransactionAddressFlat_hidden" name="autocompleteTransactionAddressFlat_hidden" />
                </div>
            </div>
            <div class="form-group">
                <label>Type</label>
                <select class="boxsize" name="transactionType" id='insertTransactionInfoForm_transactionType'>
                    <option value="sell">Sell</option>
                    <option value="rent">Rent</option>
                </select>
            </div>
            <div class="form-group">
                <label>Price</label>
                $<input type="text" class="form-control" id='insertTransactionInfoForm_transactionPrice' name="transactionPrice" placeholder="Enter Price" />
            </div>
            <div class="form-group">
                <label>Commission</label>
                $<input type="text" class="form-control" id='insertTransactionInfoForm_transactionCommission' name="transactionCommision" placeholder="Enter Commission" />
            </div>
        </div><!-- /.box-body -->

        <div class="box-footer">
            <button type="button" class="btn btn-primary" id="insertTransactionInfoForm_submit">Submit</button>
            <button type="reset" class="btn btn-primary">Reset</button>
        </div>
    </form>
</div><!-- /.box -->

<div class="box box-primary" hidden="" id="insertContactRecordForm">
    <div class="box-header">
        <h3 class="box-title">New Contact Record Form</h3>
    </div><!-- /.box-header -->
    <!-- form start -->
    <form id="insertContactRecord">
        <div class="box-body">
            <label>Date time</label>
            <div class="form-group">
                <input id="insertContactRecordForm_contactDate" type="datetime-local" name="date" />
            </div>
            <div class="form-group">
                <label>Contact Person</label>
                <div class="ui-widget">
                    <select class="boxsize" name="contactPerson" id='insertContactRecordForm_contactPerson'>
                        <option value="blank">----------</option>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label>Stock Number</label>
                <div class="ui-widget">
                    <input id="autocompleteContactRecordStock" name="stockNumber" placeholder="Enter stock number" />
                </div>
            </div>
            <div class="form-group">
                <label>Type</label>
                <select class="boxsize" name="type" id='insertContactRecordForm_type'>
                    <option value="sell">Sell</option>
                    <option value="rent">Rent</option>
                </select>
            </div>
            <div class="form-group">
                <label>Price</label>
                $<input type="text" class="form-control" id='insertContactRecordForm_price' name="price" placeholder="Enter Price" />
            </div>
            <div class="form-group">
                <label>Memo</label>
                $<textarea class="form-control" id='insertContactRecordForm_memo' name="memo" placeholder="Enter Memo" ></textarea>
            </div>
        </div><!-- /.box-body -->

        <div class="box-footer">
            <button type="button" class="btn btn-primary" id="insertContactRecordForm_submit">Submit</button>
            <button type="reset" class="btn btn-primary">Reset</button>
        </div>
    </form>
</div><!-- /.box -->

<div class="box box-primary" hidden="" id="transactionAgreementForm">
    <div class="box box-warning"><h4 class="box-title">Upload</h4>
        <div id="manageTransactionAgreement"></div>
    </div>
    <div class="box box-warning"><h4 class="box-title">Agreement</h4>
        <div id="pdf">

        </div>
    </div>
    
    <!-- form start -->
</div><!-- /.box -->

<div id="detail" hidden="">
    <div class="pull-left" id="clientDetailContainer">
    </div>
    <div class="pull-left">
        <div id="client_tab_control">
        </div>
        <div class="tab-content" id="clientDetail">
        </div>
    </div>

    <div class="connectedSortable">

    </div><!-- /.col -->
</div>

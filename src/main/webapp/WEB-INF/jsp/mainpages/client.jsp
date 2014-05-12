<!--<div class="col-xs-12 connectedSortable">
    <button id="showClientList" class="btn btn-primary">Show Client List</button>
</div> /.col -->

<div id="list" hidden="">
    <table cellpadding="9" cellspacing="9" border="0" id="listClient">
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

<div id="detail" hidden="">
    <div id="client_tab_control">
    </div>
    <div class="tab-content" id="clientDetail">
    </div>

    <div class="connectedSortable">
        
    </div><!-- /.col -->
</div>

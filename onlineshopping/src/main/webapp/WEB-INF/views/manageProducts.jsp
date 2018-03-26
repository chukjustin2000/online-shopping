<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<div class="container">
	<div class="row">
		<c:if test="${not empty message}">
			<div class="col-xs-12">
				<div class="alert alert-success alert-dismissible">
					<button type="button" class="close" data-dismiss="alert">&times;</button>
					
					${message}
					
				</div>
			
			</div>
		</c:if>
		
		
		<div class="col-md-offset-2 col-md-8">
		
			<div class="panel panel-primary">
			
			
				<div class="panel-heading">
				
					<h4>Product Management</h4>
				
				</div>
				<div class="panel-body">
				
					<!-- FORM ELEMENTS -->
					<sf:form class="form-horizontal" modelAttribute="product" action="${contextRoot}/manage/products" method="POST">
					
						<div class="form-group">
							<label class="control-label col-md-4" for="name">Enter Product Name</label>
							<div class="col-md-8">
							
								<sf:input type="text" path="name" id="name" placeholder="Poduct Name" class="form-control"/>
								
								<!-- <em class="help-block" style="color: #ff0000">Please Enter Product Name !</em> -->
							</div>
		
						</div>
						
						<div class="form-group">
							<label class="control-label col-md-4" for="name">Enter Brand Name</label>
							<div class="col-md-8">
							
								<sf:input type="text" path="brand" id="brand" placeholder="Brand Name" class="form-control"/>
								
							</div>	
						</div>
						<div class="form-group">
							<label class="control-label col-md-4" for="description">Production Description: </label>
							<div class="col-md-8">
							
								<sf:textarea  path="description" id="description" placeholder="Write a description" class="form-control"/>
							</div>	
						</div>
						
						<div class="form-group">
							<label class="control-label col-md-4" for="unitPrice">Enter Unit Price</label>
							<div class="col-md-8">
							
								<sf:input type="number" path="unitPrice" id="unitPrice" placeholder="Unit Price in &#8377;" class="form-control"/>
							
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-4" for="quantity">Quantity Available: </label>
							<div class="col-md-8">
							
								<input type="number" name="quantity" id="quantity" placeholder="Select Quantity: " class="form-control"/>
							
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-4" for="quantity">Select Category: </label>
							<div class="col-md-8">
							
								<sf:select class="form-control" path="categoryId" 
								items="${categories}"
								itemLabel="name"
								itemValue="id"/> 
									
								
							
							</div>
						</div>
						
						<div class="form-group">
							
							<div class="col-md-offset-4 col-md-8">
							
								<input type="submit" name="submit" id="brand" value="Submit" class="btn btn-primary"/>
							</div>
		
						</div>
						<!-- Hidden fields for products -->
						<sf:hidden path="id"/>
						<sf:hidden path="code"/>
						<sf:hidden path="supplierId"/>
						<sf:hidden path="active"/>
						<sf:hidden path="purchases"/>
						<sf:hidden path="views"/>
					
					</sf:form>
				
				
				</div>

			</div>
		</div>
	</div>
</div>
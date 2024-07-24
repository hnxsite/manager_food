package com.example.manager_food.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.manager_food.R;
import com.example.manager_food.model.OrderItem;

import java.util.List;

public class OngoingOrdersAdapter extends RecyclerView.Adapter<OngoingOrdersAdapter.OngoingOrderViewHolder> {

    private final Context context;
    private final List<OrderItem> orderList;

    public OngoingOrdersAdapter(Context context, List<OrderItem> orderList) {
        this.context = context;
        this.orderList = orderList;
    }

    @NonNull
    @Override
    public OngoingOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.frag_ongoing_order, parent, false);
        return new OngoingOrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OngoingOrderViewHolder holder, int position) {
        OrderItem order = orderList.get(position);
        holder.bind(order);
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    static class OngoingOrderViewHolder extends RecyclerView.ViewHolder {
        private final TextView customerName;
        private final TextView orderDate;
        private final TextView orderId;
        private final TextView orderTotal;
        private final TextView itemName;
        private final TextView itemQuantity;
        private final TextView itemPrice;
        private final TextView orderMessage;

        public OngoingOrderViewHolder(View itemView) {
            super(itemView);
            customerName = itemView.findViewById(R.id.customer_name_ongoing);
            orderDate = itemView.findViewById(R.id.order_date_ongoing);
            orderId = itemView.findViewById(R.id.order_id_ongoing);
            orderTotal = itemView.findViewById(R.id.order_total_ongoing);
            itemName = itemView.findViewById(R.id.item_name_ongoing);
            itemQuantity = itemView.findViewById(R.id.item_quantity_ongoing);
            itemPrice = itemView.findViewById(R.id.item_price_ongoing);
            orderMessage = itemView.findViewById(R.id.order_message_ongoing);
        }

        public void bind(OrderItem order) {
            customerName.setText(order.getCustomerName());
            orderDate.setText(order.getOrderDate());
            orderId.setText(order.getOrderId());
            orderTotal.setText(order.getOrderTotal());
            itemName.setText(order.getItemName());
            itemQuantity.setText(order.getItemQuantity());
            itemPrice.setText(order.getItemPrice());
            orderMessage.setText(order.getOrderMessage());
        }
    }
}

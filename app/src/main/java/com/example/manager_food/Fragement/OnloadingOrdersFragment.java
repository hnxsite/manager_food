package com.example.manager_food.Fragement;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.manager_food.Adapter.NewOrdersAdapter;
import com.example.manager_food.Adapter.OnloadingOrdersAdapter;
import com.example.manager_food.NewOrderActivity;
import com.example.manager_food.OnloadingOrderActivity;
import com.example.manager_food.R;
import com.example.manager_food.model.OrderItem;

import java.util.ArrayList;
import java.util.List;

public class OnloadingOrdersFragment extends Fragment implements NewOrdersAdapter.OnOrderClickListener {

    private RecyclerView recyclerViewOrders;
    private OnloadingOrdersAdapter OnloadingOrdersAdapter;
    private List<OrderItem> orderList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cancelled_orders, container, false);

        recyclerViewOrders = view.findViewById(R.id.recyclerViewCancelled);
        recyclerViewOrders.setLayoutManager(new LinearLayoutManager(getContext()));

        if (getArguments() != null) {
            orderList = getArguments().getParcelableArrayList("orders");
            orderList = filterOnloadingOrders(orderList);

        } else {
            orderList = new ArrayList<>();
        }

        List<OrderItem> filteredOrders = filterOnloadingOrders(orderList);

        OnloadingOrdersAdapter = new OnloadingOrdersAdapter(getContext(), filteredOrders,this::onOrderClick);
        recyclerViewOrders.setAdapter(OnloadingOrdersAdapter);

        return view;
    }

    private List<OrderItem> filterOnloadingOrders(List<OrderItem> orders) {
        List<OrderItem> ongoingOrders = new ArrayList<>();
        if (orders != null) {
            for (OrderItem order : orders) {
                if ("قيد التسليم".equals(order.getOrderStatus())) {
                    ongoingOrders.add(order);
                }
            }
        }
        return ongoingOrders;
    }

    public static OnloadingOrdersFragment newInstance(List<OrderItem> orders) {
        OnloadingOrdersFragment fragment = new OnloadingOrdersFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList("orders", new ArrayList<>(orders));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onOrderClick(OrderItem order) {
        Intent intent = new Intent(getActivity(), OnloadingOrderActivity.class);
        intent.putExtra("CUSTOMER_NAME", order.getCustomerName());
        intent.putExtra("ORDER_DATE", order.getOrderDate());
        intent.putExtra("ORDER_TOTAL", order.getOrderTotal());
        intent.putExtra("ORDER_MESSAGE", order.getOrderMessage());
        intent.putExtra("ORDER_STATUS", order.getOrderStatus());
        startActivity(intent);
    }
}
